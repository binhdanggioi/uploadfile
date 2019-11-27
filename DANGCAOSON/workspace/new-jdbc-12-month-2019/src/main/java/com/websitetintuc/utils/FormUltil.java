package com.websitetintuc.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUltil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> jClass, HttpServletRequest request) {
		T object = null;
		try {
			object = jClass.newInstance();
			try {
				BeanUtils.populate(object, request.getParameterMap());
			} catch (InvocationTargetException e) {
				System.out.print(e.getMessage());
			}
		} catch (InstantiationException e) {
			System.out.print(e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
