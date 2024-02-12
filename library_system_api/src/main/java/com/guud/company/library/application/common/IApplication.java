package com.guud.company.library.application.common;

import com.guud.company.library.core.payload.EntityFilterRequest;
import java.util.List;

/**
 * @author Chethan G.S
 *
 * @param <D>
 * @param <E>
 */

public interface IApplication<D, E> {

	/**
	 * Creates new application.
	 *
	 *
	 */
	public D newApp(String parentId) throws Exception;

	/**
	 * Creates a new application.
	 *
	 * @param dto
	 *
	 * @throws Exception
	 */
	public D createApp(D dto) throws Exception;

	/**
	 * Creates an application object cast to specific application dto.
	 *
	 * @param object
	 *
	 * @throws Exception
	 */
	public D createAppObj(Object object) throws Exception;

	/**
	 * Retrieves the application details.
	 * 
	 * @param key
	 *
	 * @throws Exception
	 */
	public D getApp(String key) throws Exception;

	/**
	 * Updates application details.
	 * 
	 * @param dto
	 *
	 * @throws Exception
	 */
	public D updateApp(D dto) throws Exception;

	/**
	 * Updates an application object cast to specific application dto.
	 * 
	 * @param object
	 *
	 * @throws Exception
	 */
	public Object updateAppObj(Object object)
			throws Exception;

	/**
	 * Deletes an application.
	 * 
	 * @param dto
	 *
	 * @throws Exception
	 */
	public D delete(D dto) throws Exception;

	/**
	 * Deletes an application object cast to specific application dto.
	 * 
	 * @param object
	 *
	 * @throws Exception
	 */
	public D deleteObj(Object object)
			throws Exception;

	/**
	 * Submits an application.
	 * 
	 * @throws @throws EntityNotFoundException @throws
	 *                            ProcessingException @throws Exception @throws
	 */
	public D confirmApp(D dto) throws Exception;

	public Object confirmAppObj(Object dto) throws Exception;

	/**
	 * Sets the filter criteria for retrieving application.
	 * 
	 * @param filterRequest
	 *
	 * @throws Exception
	 */
	public List<D> filterBy(EntityFilterRequest filterRequest)
			throws Exception;

	/**
	 * Returns the number of records.
	 * 
	 * @param dto
	 *
	 * @throws Exception
	 */
	public int countByAnd(D dto) throws Exception;

	/**
	 * Retrieves lists of records.
	 * 
	 * @param dto
	 * @param selectClause
	 * @param orderByClause
	 * @param limit
	 * @param offset
	 * 
	 *
	 * @throws Exception
	 */
	public List<E> findEntitiesByAnd(D dto, String selectClause, String orderByClause, int limit, int offset)
			throws Exception;

}
