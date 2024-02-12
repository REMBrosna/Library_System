package com.guud.company.library.core;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;

import java.util.List;
import java.util.Map;

public interface GenericDao <E, K> {
    void add(E var1) throws Exception;

    void saveOrUpdate(E var1) throws Exception;

    void update(E var1) throws Exception;

    void remove(E var1) throws Exception;

    E find(K var1) throws Exception;

    int count(Map<String, Object> var1, Map<String, String> var2, Map<String, JoinType> var3, Map<String, Object> var4, Map<String, Object> var5, CriteriaWrapper[] var6) throws Exception;

    int count(Map<String, Object> var1, Map<String, String> var2, Map<String, Object> var3, Map<String, Object> var4, CriteriaWrapper[] var5) throws Exception;

    int count(Map<String, Object> var1, Map<String, String> var2, Map<String, Object> var3, Map<String, Object> var4) throws Exception;

    int count(Map<String, Object> var1, Map<String, Object> var2, Map<String, Object> var3) throws Exception;

    int count(String var1, Map<String, Object> var2) throws Exception;

    int count(String var1) throws Exception;

    List<E> getAll() throws Exception;

    List<E> getAll(List<String> var1, List<String> var2) throws Exception;

    List<E> getByQuery(String var1) throws Exception;

    List<E> getByQuery(String var1, Map<String, Object> var2) throws Exception;

    List<E> getByQuery(String var1, Map<String, Object> var2, int var3, int var4) throws Exception;

    List<E> getByCriteria(Map<String, Object> var1, Map<String, String> var2, Map<String, JoinType> var3, List<String> var4, List<String> var5, int var6, int var7, CriteriaWrapper[] var8) throws Exception;

    List<E> getByCriteria(Map<String, Object> var1, Map<String, String> var2, List<String> var3, List<String> var4, int var5, int var6, CriteriaWrapper[] var7) throws Exception;

    List<E> getByCriteria(Map<String, Object> var1, Map<String, String> var2, List<String> var3, List<String> var4, int var5, int var6) throws Exception;

    List<E> getByCriteria(Map<String, Object> var1, List<String> var2, List<String> var3, int var4, int var5) throws Exception;

    List<E> getByLikeCriteria(Map<String, Object> var1, Map<String, Object> var2, Map<String, Object> var3, List<String> var4, List<String> var5, int var6, int var7) throws Exception;

    List<E> getByCriteria(DetachedCriteria var1) throws Exception;

    E getOne(DetachedCriteria var1) throws Exception;

    List<E> getByNativeSQL(String var1) throws Exception;

    int executeNativeSQL(String var1) throws Exception;

    int executeNativeSQL(String var1, Map<String, Object> var2) throws Exception;

    List<Integer> nativeSQL(String var1) throws Exception;

    List<Integer> nativeSQL(String var1, Map<String, Object> var2) throws Exception;

    int executeUpdate(String var1, Map<String, Object> var2) throws Exception;
}
