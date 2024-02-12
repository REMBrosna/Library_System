package com.guud.company.library.event;
import com.guud.company.library.application.apps.model.TApplication;
import org.springframework.context.ApplicationEvent;

public class OverBorrowEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4240196949502143L;
    protected TApplication application;

    public OverBorrowEvent(Object source) {
        super(source);
    }

    public TApplication getApplication() {
        return application;
    }

    public void setApplication(TApplication application) {
        this.application = application;
    }
}
