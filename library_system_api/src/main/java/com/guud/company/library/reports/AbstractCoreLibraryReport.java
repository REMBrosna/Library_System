package com.guud.company.library.reports;

import com.guud.company.library.reports.model.TReportServiceConfig;
import com.guud.company.library.reports.model.TReportTemplateConfig;
import com.guud.company.library.reports.service.ReportServiceConfigService;
import com.guud.company.library.reports.service.ReportTemplateConfigService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j2
public abstract class AbstractCoreLibraryReport implements ILibraryReport {

    // Static Attributes
    ////////////////////
    protected static String PARAM_INFO = "PARAM_INFO";
    // Attributes
    ////////////////////
    @Autowired
    private ReportTemplateConfigService reportTemplateConfigService;
    @Autowired
    private ReportServiceConfigService reportServiceConfigService;

    //mapping meta param from table configuration
    private Map<String, Object> metaParam(Long serviceId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        List<TReportTemplateConfig> templateList = getReportTemplateByServiceId(serviceId);
        templateList.forEach(tReportTemplateConfig -> {
            //get main template path
            if (tReportTemplateConfig.getRetTemplateType().equalsIgnoreCase("MAIN")) {
                try {
                    if (StringUtils.isNotBlank(tReportTemplateConfig.getRetTemplatePath())){
                        File mainReportFile = ResourceUtils.getFile("classpath:" + tReportTemplateConfig.getRetTemplatePath());
                        param.put("mainPath", mainReportFile.getAbsolutePath());
                    }
                    if (StringUtils.isNotBlank(tReportTemplateConfig.getRetTemplateLogo())){
                        File logoReportFile = ResourceUtils.getFile("classpath:" + tReportTemplateConfig.getRetTemplateLogo());
                        param.put("logoPath", logoReportFile.getAbsolutePath());
                    }
                } catch (FileNotFoundException e) {
                    log.error("error metaParam main", e);
                }
            } else if (tReportTemplateConfig.getRetTemplateType().equalsIgnoreCase("SUB")) {
                //get list of sub template path
                try {
                    if (StringUtils.isNotBlank(tReportTemplateConfig.getRetTemplatePath())){
                        File subReportFile = ResourceUtils.getFile("classpath:" + tReportTemplateConfig.getRetTemplatePath());
                        param.put(tReportTemplateConfig.getRetSubTemplate(), subReportFile.getAbsolutePath());
                    }
                } catch (FileNotFoundException e) {
                    log.error("error metaParam sub", e);
                }
            }
        });
        return param;
    }

    // export pdf without listing items
    protected byte[] exportPDF(Long serviceId, Map<String, Object> parameters, ByteArrayOutputStream byteStream) throws Exception {
        //put meta param
        Map<String, Object> metaParam = metaParam(serviceId);
        Map<String, Object> param = new HashMap<>();
        param.put(PARAM_INFO, metaParam);
        param.putAll(parameters);
        JasperReport jasperReport = JasperCompileManager.compileReport(metaParam.get("mainPath").toString());
        JasperExportManager.exportReportToPdfStream(JasperFillManager.fillReport(jasperReport, param), byteStream);
        return byteStream.toByteArray();
    }

    // export pdf with listing items
    protected byte[] exportPDF(Long serviceId, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource, ByteArrayOutputStream byteStream) throws Exception {
        //put meta param
        Map<String, Object> metaParam = metaParam(serviceId);
        Map<String, Object> param = new HashMap<>();
        param.put(PARAM_INFO, metaParam);
        param.putAll(parameters);
        JasperReport jasperReport = JasperCompileManager.compileReport(metaParam.get("mainPath").toString());
        JasperExportManager.exportReportToPdfStream(JasperFillManager.fillReport(jasperReport, param, dataSource), byteStream);
        return byteStream.toByteArray();
    }

    //fetch service id by service name
    protected Long getReportServiceByValAndServiceName(String val, String serviceName) throws Exception {
        TReportServiceConfig tReportServiceConfig = reportServiceConfigService.getReportServiceByValAndServiceName(val, serviceName);
        return Objects.nonNull(tReportServiceConfig) ? tReportServiceConfig.getResId() : null;
    }

    //fetch report template configure from table by service id
    protected List<TReportTemplateConfig> getReportTemplateByServiceId(Long serviceId) throws Exception {
        return reportTemplateConfigService.getReportTemplateByServiceId(serviceId);
    }

    //fetch excel template by service id
    protected byte[] exportExcel(Long serviceId, String filter) throws Exception {
        List<TReportTemplateConfig> templateConfigs = getReportTemplateByServiceId(serviceId);
        if (!templateConfigs.isEmpty()){
            File file = ResourceUtils.getFile("classpath:" + templateConfigs.get(0).getRetTemplatePath());
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            excelRowMapping(workbook, filter);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            workbook.write(bOut);
            workbook.close();
            return bOut.toByteArray();
        }
        return null;
    }
}
