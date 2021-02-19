/** 
 * (C) Copyright 2018 BioCliq Technologies Pvt. Ltd India. All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential 
 *
 * Author k.raja@biocliq.com
 */

package com.zitlab.mobyra.library.pojo;

/**
 * The type Action.
 */
public final class Action {
	
	private Action() {}

    /**
     * The constant CREATE_S.
     */
    public static final String CREATE_S = "create";
    /**
     * The constant UPDATE_S.
     */
    public static final String UPDATE_S = "update";
    /**
     * The constant DELETE_S.
     */
    public static final String DELETE_S = "delete";
    /**
     * The constant SAVE_S.
     */
    public static final String SAVE_S = "save";
    /**
     * The constant NONE_S.
     */
    public static final String NONE_S = "none";

    /**
     * The constant PARENT_ACTION.
     */
    public static final int PARENT_ACTION = 0;
    /**
     * The constant UPDATE.
     */
    public static final int UPDATE = 1;
    /**
     * The constant CREATE.
     */
    public static final int CREATE = 2;
    /**
     * The constant DELETE.
     */
    public static final int DELETE = -1;
    /**
     * The constant NONE.
     */
    public static final int NONE = -2;
    /**
     * The constant SAVE.
     */
    public static final int SAVE = 3;

    /**
     * Get string string.
     *
     * @param action the action
     * @return the string
     */
    public static String getString(int action){
		switch(action) {
		case CREATE:
			return CREATE_S;
		case UPDATE:
			return UPDATE_S;
		case DELETE:
			return DELETE_S;
		case NONE:
			return NONE_S;
		case SAVE:
			return SAVE_S;
		default:
			return null;
		}
	}

    /**
     * Gets int.
     *
     * @param action the action
     * @return the int
     */
    public static Integer getInt(String action) {
		switch (action) {
		case CREATE_S:
			return CREATE;
		case DELETE_S:		
			return DELETE;
		case UPDATE_S:
			return UPDATE;
		case NONE_S:
			return NONE;
		case SAVE_S:
			return SAVE;
		default:
			return null;
		}
	}

    /**
     * Gets rel int.
     *
     * @param action the action
     * @return the rel int
     */
    public static Integer getRelInt(String action) {
		switch (action) {
		case CREATE_S:
			return CREATE;
		case DELETE_S:		
			return DELETE;
		case UPDATE_S:
			return UPDATE;
		case SAVE_S:
			return SAVE;
		default:
			return PARENT_ACTION;
		}
	}
}
