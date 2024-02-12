package com.guud.company.library.core;

import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.Cache;
import org.hibernate.Query;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.sql.JoinType;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.*;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	// Static Attributes
	///////////////////////
	private static final Logger log = Logger.getLogger(GenericDaoImpl.class);

	// Attributes
	/////////////
	@PersistenceContext
	private EntityManager entityManager;
	protected Class<? extends E> daoType;

	// Constructor
	//////////////
	@SuppressWarnings("rawtypes")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	// Interface Methods
	/////////////////////
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void add(E entity) throws Exception {
		log.debug("add():...");
		try {
			if (null == entity)
				throw new Exception("param entity null");
			if (null == currentSession())
				throw new Exception("session null");

			currentSession().save(entity);
		} catch (Exception ex) {
			log.error("add", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void saveOrUpdate(E entity) throws Exception {
		log.debug("saveOrUpdate");
		try {
			if (null == entity)
				throw new Exception("param entity null");
			if (null == currentSession())
				throw new Exception("session null");

			currentSession().saveOrUpdate(entity);
		} catch (Exception ex) {
			log.error("saveOrUpdate", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void update(E entity) throws Exception {
		log.debug("update():...");
		try {
			if (null == entity)
				throw new Exception("param entity null");
			if (null == currentSession())
				throw new Exception("session null");

			currentSession().saveOrUpdate(entity);
		} catch (Exception ex) {
			log.error("update", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void remove(E entity) throws Exception {
		log.debug("remove():...");
		try {
			if (null == entity)
				throw new Exception("param entity null");
			if (null == currentSession())
				throw new Exception("session null");

			currentSession().delete(entity);
		} catch (Exception ex) {
			log.error("remove", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public E find(K key) throws Exception {
		log.debug("find():...");
		try {
			if (null == key)
				throw new Exception("param key null");
			if (null == currentSession())
				throw new Exception("session null");

			return (E) currentSession().get(daoType, key);
		} catch (Exception ex) {
			log.error("find", ex);
			throw ex;
		}
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(Map<String, Object> criterias, Map<String, String> aliases, Map<String, JoinType> aliasJoinType,
                     Map<String, Object> fieldDateStartCriteria, Map<String, Object> fieldDateEndCriteria,
                     CriteriaWrapper[] criteriaWrappers) throws Exception {

		if (aliasJoinType == null)
			aliasJoinType = new HashMap<>();

		try {
			if (null == currentSession())
				throw new Exception("session null");

			Criteria crit = currentSession().createCriteria(daoType);

			if (null != aliases) {
				for (Map.Entry<String, String> e : aliases.entrySet()) {
					String aliasKey = e.getKey();
					if (aliasJoinType.containsKey(aliasKey)) {
						crit.createAlias(aliasKey, e.getValue(), aliasJoinType.get(aliasKey));
					} else {
						crit.createAlias(aliasKey, e.getValue());
					}
				}
			}
			if (null != criterias) {
				this.createCritParams(crit, criterias);
			}
			if (null != fieldDateStartCriteria) {
				for (String key : fieldDateStartCriteria.keySet()) {
					crit.add(Restrictions.ge(key, fieldDateStartCriteria.get(key)));
				}
			}
			if (null != fieldDateEndCriteria) {
				for (String key : fieldDateEndCriteria.keySet()) {
					crit.add(Restrictions.le(key, fieldDateEndCriteria.get(key)));
				}
			}
			if (null != criteriaWrappers) {
				for (CriteriaWrapper criteriaWrapper : criteriaWrappers)
					crit = criteriaWrapper.criteria(crit, new SessionFactory() {
						@Override
						public SessionFactoryOptions getSessionFactoryOptions() {
							return null;
						}

						@Override
						public SessionBuilder withOptions() {
							return null;
						}

						@Override
						public Session openSession() throws HibernateException {
							return null;
						}

						@Override
						public Session getCurrentSession() throws HibernateException {
							return null;
						}

						@Override
						public StatelessSessionBuilder withStatelessOptions() {
							return null;
						}

						@Override
						public StatelessSession openStatelessSession() {
							return null;
						}

						@Override
						public StatelessSession openStatelessSession(Connection connection) {
							return null;
						}

						@Override
						public Statistics getStatistics() {
							return null;
						}

						@Override
						public void close() throws HibernateException {

						}

						@Override
						public boolean isClosed() {
							return false;
						}

						@Override
						public Cache getCache() {
							return null;
						}

						@Override
						public Set getDefinedFilterNames() {
							return null;
						}

						@Override
						public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
							return null;
						}

						@Override
						public boolean containsFetchProfileDefinition(String name) {
							return false;
						}

						@Override
						public TypeHelper getTypeHelper() {
							return null;
						}

						@Override
						public ClassMetadata getClassMetadata(Class entityClass) {
							return null;
						}

						@Override
						public ClassMetadata getClassMetadata(String entityName) {
							return null;
						}

						@Override
						public CollectionMetadata getCollectionMetadata(String roleName) {
							return null;
						}

						@Override
						public Map<String, ClassMetadata> getAllClassMetadata() {
							return null;
						}

						@Override
						public Map getAllCollectionMetadata() {
							return null;
						}

						@Override
						public Reference getReference() throws NamingException {
							return null;
						}

						@Override
						public <T> List<EntityGraph<? super T>> findEntityGraphsByType(Class<T> entityClass) {
							return null;
						}

						@Override
						public Metamodel getMetamodel() {
							return null;
						}

						@Override
						public EntityManager createEntityManager() {
							return null;
						}

						@Override
						public EntityManager createEntityManager(Map map) {
							return null;
						}

						@Override
						public EntityManager createEntityManager(SynchronizationType synchronizationType) {
							return null;
						}

						@Override
						public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
							return null;
						}

						@Override
						public CriteriaBuilder getCriteriaBuilder() {
							return null;
						}

						@Override
						public boolean isOpen() {
							return false;
						}

						@Override
						public Map<String, Object> getProperties() {
							return null;
						}

						@Override
						public PersistenceUnitUtil getPersistenceUnitUtil() {
							return null;
						}

						@Override
						public void addNamedQuery(String name, javax.persistence.Query query) {

						}

						@Override
						public <T> T unwrap(Class<T> cls) {
							return null;
						}

						@Override
						public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {

						}
					}, daoType);
			}

			Number number = (Number) crit.setProjection(Projections.rowCount()).uniqueResult();
			return number.intValue();

		} catch (Exception ex) {
			log.error("getByCriteria", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(Map<String, Object> criterias, Map<String, String> aliases,
                     Map<String, Object> fieldDateStartCriteria, Map<String, Object> fieldDateEndCriteria,
                     CriteriaWrapper[] criteriaWrappers) throws Exception {
		return this.count(criterias, aliases, null, fieldDateStartCriteria, fieldDateEndCriteria, criteriaWrappers);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(Map<String, Object> criterias, Map<String, String> aliases,
                     Map<String, Object> fieldDateStartCriteria, Map<String, Object> fieldDateEndCriteria) throws Exception {
		return this.count(criterias, aliases, fieldDateStartCriteria, fieldDateEndCriteria, null);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(Map<String, Object> criterias, Map<String, Object> fieldDateStartCriteria,
                     Map<String, Object> fieldDateEndCriteria) throws Exception {
		return this.count(criterias, null, fieldDateStartCriteria, fieldDateEndCriteria);
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(String hql, Map<String, Object> parameters) throws Exception {
		log.debug("count by hql...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param hql null or empty");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createQuery(hql);
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					if (value instanceof Collection) {
						query.setParameterList(param, (Collection) value);
					} else {
						query.setParameter(param, value);
					}
				}
			}

			Number number = (Number) query.uniqueResult();
			return number.intValue();

		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int count(String hql) throws Exception {
		log.debug("count by hql...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param hql null or empty");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createQuery(hql);
			Number number = (Number) (query.uniqueResult() == null ? 0 : query.uniqueResult());
			return number.intValue();
		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getAll() throws Exception {
		log.debug("getAll():...");
		try {
			if (null == currentSession())
				throw new Exception("session null");

			return currentSession().createCriteria(daoType).list();
		} catch (Exception ex) {
			log.error("getAll", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getAll(List<String> fieldsAsc, List<String> fieldsDesc) throws Exception {
		log.debug("getAll():...");
		try {
			if (null == currentSession())
				throw new Exception("session null");

			Criteria crit = currentSession().createCriteria(daoType);
			if (!Collections.isEmpty(fieldsAsc)) {
				for (String field : fieldsAsc)
					crit.addOrder(Order.asc(field));
			}
			if (!Collections.isEmpty(fieldsDesc)) {
				for (String field : fieldsDesc)
					crit.addOrder(Order.desc(field));
			}

			List<E> entities = crit.list();
			return entities;
		} catch (Exception ex) {
			log.error("getAll", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByCriteria(Map<String, Object> criterias, Map<String, String> aliases,
                                 Map<String, JoinType> aliasJoinType, List<String> fieldsAsc, List<String> fieldsDesc, int limit, int offset,
                                 CriteriaWrapper[] criteriaWrappers) throws Exception {

		if (aliasJoinType == null)
			aliasJoinType = new HashMap<>();

		try {
			if (null == currentSession())
				throw new Exception("session null");

			Criteria crit = currentSession().createCriteria(daoType);
			if (!Collections.isEmpty(aliases)) {
				for (Map.Entry<String, String> e : aliases.entrySet()) {
					String aliasKey = e.getKey();
					if (aliasJoinType.containsKey(aliasKey)) {
						crit.createAlias(aliasKey, e.getValue(), aliasJoinType.get(aliasKey));
					} else {
						crit.createAlias(aliasKey, e.getValue());
					}
				}
			}

			if (!Collections.isEmpty(criterias))
				this.createCritParams(crit, criterias);

			if (!Collections.isEmpty(fieldsAsc)) {
				for (String field : fieldsAsc)
					crit.addOrder(Order.asc(field));
			}

			if (!Collections.isEmpty(fieldsDesc)) {
				for (String field : fieldsDesc)
					crit.addOrder(Order.desc(field));
			}

			if (limit != 0 || limit > 0)
				crit.setMaxResults(limit);

			if (offset > 0)
				crit.setFirstResult(offset);

			if (!ArrayUtils.isEmpty(criteriaWrappers)) {
				for (CriteriaWrapper criteriaWrapper : criteriaWrappers)
					crit = criteriaWrapper.criteria(crit, new SessionFactory() {
						@Override
						public SessionFactoryOptions getSessionFactoryOptions() {
							return null;
						}

						@Override
						public SessionBuilder withOptions() {
							return null;
						}

						@Override
						public Session openSession() throws HibernateException {
							return null;
						}

						@Override
						public Session getCurrentSession() throws HibernateException {
							return null;
						}

						@Override
						public StatelessSessionBuilder withStatelessOptions() {
							return null;
						}

						@Override
						public StatelessSession openStatelessSession() {
							return null;
						}

						@Override
						public StatelessSession openStatelessSession(Connection connection) {
							return null;
						}

						@Override
						public Statistics getStatistics() {
							return null;
						}

						@Override
						public void close() throws HibernateException {

						}

						@Override
						public boolean isClosed() {
							return false;
						}

						@Override
						public Cache getCache() {
							return null;
						}

						@Override
						public Set getDefinedFilterNames() {
							return null;
						}

						@Override
						public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
							return null;
						}

						@Override
						public boolean containsFetchProfileDefinition(String name) {
							return false;
						}

						@Override
						public TypeHelper getTypeHelper() {
							return null;
						}

						@Override
						public ClassMetadata getClassMetadata(Class entityClass) {
							return null;
						}

						@Override
						public ClassMetadata getClassMetadata(String entityName) {
							return null;
						}

						@Override
						public CollectionMetadata getCollectionMetadata(String roleName) {
							return null;
						}

						@Override
						public Map<String, ClassMetadata> getAllClassMetadata() {
							return null;
						}

						@Override
						public Map getAllCollectionMetadata() {
							return null;
						}

						@Override
						public Reference getReference() throws NamingException {
							return null;
						}

						@Override
						public <T> List<EntityGraph<? super T>> findEntityGraphsByType(Class<T> entityClass) {
							return null;
						}

						@Override
						public Metamodel getMetamodel() {
							return null;
						}

						@Override
						public EntityManager createEntityManager() {
							return null;
						}

						@Override
						public EntityManager createEntityManager(Map map) {
							return null;
						}

						@Override
						public EntityManager createEntityManager(SynchronizationType synchronizationType) {
							return null;
						}

						@Override
						public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
							return null;
						}

						@Override
						public CriteriaBuilder getCriteriaBuilder() {
							return null;
						}

						@Override
						public boolean isOpen() {
							return false;
						}

						@Override
						public Map<String, Object> getProperties() {
							return null;
						}

						@Override
						public PersistenceUnitUtil getPersistenceUnitUtil() {
							return null;
						}

						@Override
						public void addNamedQuery(String name, javax.persistence.Query query) {

						}

						@Override
						public <T> T unwrap(Class<T> cls) {
							return null;
						}

						@Override
						public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {

						}
					}, daoType);
			}

			List<E> entities = crit.list();
			return entities;
		} catch (Exception ex) {
			log.error("getByCriteria", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByCriteria(Map<String, Object> criterias, Map<String, String> aliases, List<String> fieldsAsc,
                                 List<String> fieldsDesc, int limit, int offset, CriteriaWrapper[] criteriaWrappers) throws Exception {
		return this.getByCriteria(criterias, aliases, null, fieldsAsc, fieldsDesc, limit, offset, criteriaWrappers);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByCriteria(Map<String, Object> criterias, Map<String, String> aliases, List<String> fieldsAsc,
                                 List<String> fieldsDesc, int limit, int offset) throws Exception {
		return this.getByCriteria(criterias, aliases, fieldsAsc, fieldsDesc, limit, offset, null);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByCriteria(Map<String, Object> criterias, List<String> fieldsAsc, List<String> fieldsDesc,
                                 int limit, int offset) throws Exception {
		return this.getByCriteria(criterias, null, fieldsAsc, fieldsDesc, limit, offset);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByLikeCriteria(Map<String, Object> criterias, Map<String, Object> fieldDateStartCriteria,
                                     Map<String, Object> fieldDateEndCriteria, List<String> fieldsAsc, List<String> fieldsDesc, int limit,
                                     int offset) throws Exception {
		log.debug("getByLikeCriteria():...");
		try {
			if (null == currentSession())
				throw new Exception("session null");

			String rootAlias = daoType.getSimpleName();
			Criteria crit = currentSession().createCriteria(daoType, rootAlias);

			if (!Collections.isEmpty(criterias)) {
				for (String key : criterias.keySet()) {
					if (key.contains(".")) {
						String subTypeName = key.substring(0, key.indexOf("."));
						String rootPlusSubTypeName = rootAlias + "." + subTypeName;
						String attr = subTypeName + "." + key.substring(key.indexOf(".") + 1);
						crit.createAlias(rootPlusSubTypeName, subTypeName);

						if (StringUtils.contains(String.valueOf(criterias.get(key)), "%")) {
							crit.add(Restrictions.like(attr, criterias.get(key)));
						} else {
							crit.add(Restrictions.eq(attr, criterias.get(key)));
						}
					} else {
						if (criterias.get(key) instanceof Date) {
							// Skip the date
						} else {
							if (StringUtils.contains(String.valueOf(criterias.get(key)), "%")) {
								crit.add(Restrictions.like(key, criterias.get(key)));
							} else {
								crit.add(Restrictions.eq(key, criterias.get(key)));
							}
						}
					}
				}
			}

			if (!Collections.isEmpty(fieldDateStartCriteria)) {
				for (String key : fieldDateStartCriteria.keySet()) {
					crit.add(Restrictions.ge(key, fieldDateStartCriteria.get(key)));
				}
			}
			if (!Collections.isEmpty(fieldDateEndCriteria)) {
				for (String key : fieldDateEndCriteria.keySet()) {
					crit.add(Restrictions.le(key, fieldDateEndCriteria.get(key)));
				}
			}

			if (!Collections.isEmpty(fieldsAsc)) {
				for (String field : fieldsAsc) {
					crit.addOrder(Order.asc(field));
				}
			}
			if (!Collections.isEmpty(fieldsDesc)) {
				for (String field : fieldsDesc) {
					crit.addOrder(Order.desc(field));
				}
			}
			if (limit != 0 || limit > 0) {
				crit.setMaxResults(limit);
			}
			if (offset > 0) {
				crit.setFirstResult(offset);
			}

			List<E> entities = crit.list();
			return entities;
		} catch (Exception ex) {
			log.error("getByLikeCriteria", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByQuery(String hql) throws Exception {
		log.debug("getByQuery():...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param hql empty or null");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createQuery(hql);
			List<E> results = query.list();
			return results;
		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByQuery(String hql, Map<String, Object> parameters) throws Exception {
		log.debug("getByQuery():...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param hql empty or null");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createQuery(hql);
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					if (value instanceof Collection) {
						query.setParameterList(param, (Collection) value);
					} else {
						query.setParameter(param, value);
					}
				}
			}
			List<E> results = query.list();
			return results;
		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByQuery(String hql, Map<String, Object> parameters, int limit, int offset) throws Exception {
		log.debug("getByQuery():...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param hql empty or null");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createQuery(hql);
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					if (value instanceof Collection) {
						query.setParameterList(param, (Collection) value);
					} else if (value instanceof Date) {
						query.setTimestamp(param, (Date) value);
					} else {
						query.setParameter(param, value);
					}
				}
			}

			if (limit != 0 || limit > 0) {
				query.setMaxResults(limit);
			}
			if (offset > 0) {
				query.setFirstResult(offset);
			}

			List<E> results = query.list();
			return results;
		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByCriteria(DetachedCriteria criteria) throws Exception {
		log.debug("getByQuery():...");

		try {
			if (null == currentSession())
				throw new Exception("session null");

			return criteria.getExecutableCriteria(currentSession()).list();
		} catch (Exception ex) {
			log.error("getByQuery", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public E getOne(DetachedCriteria criteria) throws Exception {
		log.debug("getOne():...");

		try {
			if (null == currentSession())
				throw new Exception("session null");

			List<E> list = criteria.getExecutableCriteria(currentSession()).setMaxResults(1).list();
			return list.size() > 0 ? list.get(0) : null;
		} catch (Exception ex) {
			log.error("getOne", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<E> getByNativeSQL(String nativeSQL) throws Exception {
		log.debug("getByNativeSQL():...");

		try {
			if (null == currentSession())
				throw new Exception("session null");

			SQLQuery query = currentSession().createSQLQuery(nativeSQL);
			return query.list();
		} catch (Exception ex) {
			log.error("getByNativeSQL", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int executeNativeSQL(String nativeSQL) throws Exception {
		log.debug("executeNativeSQL():...");

		try {
			if (null == currentSession())
				throw new Exception("session null");

			SQLQuery query = currentSession().createSQLQuery(nativeSQL);
			Number value = (Number) query.uniqueResult();
			return value.intValue();
		} catch (Exception ex) {
			log.error("executeNativeSQL", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int executeNativeSQL(String nativeSQL, Map<String, Object> parameters) throws Exception {
		log.debug("executeNativeSQL():...");
		
		try {
			if (null == currentSession())
				throw new Exception("session null");
			
			Query query = currentSession().createSQLQuery(nativeSQL);
			
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					query.setParameter(param, value);
				}
			}
			
			Number value = (Number) query.uniqueResult();
			return value.intValue();
		} catch (Exception ex) {
			log.error("executeNativeSQL", ex);
			throw ex;
		}
		
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Integer> nativeSQL(String nativeSQL) throws Exception {
		log.debug("nativeSQL():...");

		try {
			if (StringUtils.isEmpty(nativeSQL))
				throw new Exception("param nativeSQL null or empty");
			if (null == currentSession())
				throw new Exception("session null");

			Query query = currentSession().createSQLQuery(nativeSQL);
			List<Integer> integerList = query.list();
			return integerList;
		} catch (Exception ex) {
			log.error("nativeSQL", ex);
			throw ex;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Integer> nativeSQL(String nativeSQL, Map<String, Object> parameters) throws Exception {
		log.debug("nativeSQL():...");

		try {
			if (StringUtils.isEmpty(nativeSQL))
				throw new Exception("param nativeSQL null or empty");
			if (null == currentSession())
				throw new Exception("session null");
			
			Query query = currentSession().createSQLQuery(nativeSQL);
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					query.setParameter(param, value);
				}
			}
			
			List<Integer> integerList = query.list();
			return integerList;
		} catch (Exception ex) {
			log.error("nativeSQL", ex);
			throw ex;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int executeUpdate(String hql, Map<String, Object> parameters) throws Exception {
		log.debug("executeUpdate():...");
		try {
			if (StringUtils.isEmpty(hql))
				throw new Exception("param nativeSQL null or empty");
			if (null == currentSession())
				throw new Exception("session null");
			
			Query query = currentSession().createQuery(hql);
			if (!Collections.isEmpty(parameters)) {
				for (String param : parameters.keySet()) {
					Object value = parameters.get(param);
					if (value instanceof Collection) {
						query.setParameterList(param, (Collection) value);
					} else {
						query.setParameter(param, value);
					}
				}
			}
			return query.executeUpdate();
		} catch (Exception ex) {
			log.error("executeUpdate", ex);
			throw ex;
		}
	}

	private void createCritParams(Criteria crit, Map<String, Object> criterias) throws Exception {
		log.debug("createCritParams");

		try {
			if (null == crit)
				throw new Exception("param crit null");

			if (!Collections.isEmpty(criterias)) {
				for (String key : criterias.keySet()) {
					try {
						Object criteria = criterias.get(key);
						if (criteria instanceof String) {
							String criteriaStr = (String) criteria;
							if (criteriaStr.startsWith("%")) {
								crit.add(Restrictions.like(key, criteriaStr.substring(1)));
							} else if (criteriaStr.startsWith("!")) {
								crit.add(Restrictions.not(Restrictions.eq(key, criteriaStr.substring(1))));
							} else if (criteriaStr.startsWith("^")) {

								String[] critCollection = StringUtils.split(criteriaStr.substring(1), ';');
								Object[] critCharCollection = new Character[critCollection.length];
								for (int i = 0; i < critCollection.length; i++) {
									String str = critCollection[i];
									if (str.length() == 1) {
										critCharCollection[i] = str.toCharArray()[0];
									}
								}
								crit.add(Restrictions.in(key, critCharCollection));

							} else if (criteriaStr.startsWith("$")) {
								Object[] critCollection = StringUtils.split(criteriaStr.substring(1), ';');
								crit.add(Restrictions.in(key, critCollection));
							} else {
								crit.add(Restrictions.eq(key, criteriaStr));
							}
						} else {
							crit.add(Restrictions.eq(key, criterias.get(key)));
						}
					} catch (Exception ex) {
						log.error("createCritParams", ex);
					}
				}
			}
		} catch (Exception ex) {
			log.error("createCritParams", ex);
			throw ex;
		}

	}

	public Session currentSession() {
		return entityManager.unwrap(Session.class);
	}
}