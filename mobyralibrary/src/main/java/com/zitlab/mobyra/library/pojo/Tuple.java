package com.zitlab.mobyra.library.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Tuple.
 */
public class Tuple {

    // The primary key of the Tuple.
    private String id;

    /**
     * The Type.
     */
    protected String type;

    private Integer action;

    private String label;

    /**
     * Parent configuration items. these tuples will not be altered unless mentioned
     * by the action flag
     */
    protected HashMap<String, Tuple> parent = new LinkedHashMap<String, Tuple>();

    /**
     * The Children.
     */
    protected HashMap<String, List<Tuple>> children = new LinkedHashMap<String, List<Tuple>>();

    /**
     * The Attributes.
     */
    protected HashMap<String, Object> attributes = new LinkedHashMap<String, Object>();

    /**
     * Instantiates a new Tuple.
     */
    public Tuple() {

    }

    /**
     * Instantiates a new Tuple.
     *
     * @param type the type
     */
    public Tuple(String type) {
        this.type = type;
    }

    /**
     * Instantiates a new Tuple.
     *
     * @param type the type
     * @param id   the id
     */
    public Tuple(String type, String id) {
        this.type = type;
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        if(null != id) {
            if(0 == id.trim().length()) {
                id = null;
            }
        }
        this.id = id;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public HashMap<String, Tuple> getParent() {
        return parent;
    }

    /**
     * Gets parent.
     *
     * @param key the key
     * @return the parent
     */
    public Tuple getParent(String key) {
        return parent.get(key);
    }

    /**
     * Sets parent.
     *
     * @param parent the parent
     */
    public void setParent(HashMap<String, Tuple> parent) {
        this.parent = parent;
    }

    /**
     * Add parent.
     *
     * @param key   the key
     * @param value the value
     */
    public void addParent(String key, Tuple value) {
        this.parent.put(key, value);
    }

    /**
     * Remove parent.
     *
     * @param key the key
     */
    public void removeParent(String key) {
        this.parent.remove(key);
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public HashMap<String, List<Tuple>> getChildren() {
        return children;
    }

    /**
     * Sets children.
     *
     * @param children the children
     */
    public void setChildren(HashMap<String, List<Tuple>> children) {
        this.children = children;
    }

    /**
     * Add children.
     *
     * @param key   the key
     * @param tuple the tuple
     */
    public void addChildren(String key, Tuple tuple) {
        List<Tuple> childList = children.get(key);
        if(null != childList) {
            childList.add(tuple);
            return;
        }else {
            childList = new ArrayList<Tuple>();
            children.put(key, childList);
            childList.add(tuple);
        }
    }

    /**
     * Sets children.
     *
     * @param key    the key
     * @param tuples the tuples
     */
    public void setChildren(String key, List<Tuple> tuples) {
        children.remove(key);
        children.put(key, tuples);
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * Sets attribute.
     *
     * @param key   the key
     * @param value the value
     */
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * Gets attribute.
     *
     * @param key the key
     * @return the attribute
     */
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    /**
     * Gets metainfo.
     *
     * @return the metainfo
     */
    public TupleMetaInfo getMetainfo() {
        if(null == label)
            return null;

        TupleMetaInfo metaInfo = new TupleMetaInfo();
        metaInfo.setType(type);
        metaInfo.setLabel(label);

        return metaInfo;
    }

    /**
     * Sets metainfo.
     *
     * @param metainfo the metainfo
     */
    public void setMetainfo(TupleMetaInfo metainfo) {
        this.action = metainfo.getActionCode();
        this.type = metainfo.getType();
    }

    /**
     * Is id empty boolean.
     *
     * @return the boolean
     */
    public boolean isIdEmpty() {
        return null == id;
    }

    /**
     * For delete boolean.
     *
     * @return the boolean
     */
    public boolean forDelete() {
        return null == this.action ? false : this.action == Action.DELETE;
    }

    /**
     * Gets action code.
     *
     * @return action code
     */
    public int getActionCode() {
        return null == this.action ? Action.NONE : this.action;
    }

    /**
     * Sets action code.
     *
     * @param code the code
     */
    public void setActionCode(int code) {
        this.action = code;
    }

    /**
     * Has attribute boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }

    /**
     * Gets attribute as string.
     *
     * @param name the name
     * @return the attribute as string
     */
    public String getAttributeAsString(String name) {
        Object obj = getAttribute(name);
        if (null == obj)
            return null;
        else if (obj instanceof String)
            return (String) obj;
        else
            return obj.toString();
    }

    /**
     * For create boolean.
     *
     * @return boolean
     */
    public boolean forCreate() {
        return null == this.action ? false : this.action == Action.CREATE;
    }

    /**
     * Sets ref attribute.
     *
     * @param field the field
     * @param value the value
     */
    public void setRefAttribute(String field, Object value) {
        int index = field.indexOf('.');
        if(index < 0) {
            this.setAttribute(field, value);
        }else {
            String ref = field.substring(0, index);
            String _field = field.substring(index +1);
            Tuple reference = parent.get(ref);
            if(null == reference) {
                reference = new Tuple();
                parent.put(ref, reference);
            }
            if(_field.equals("id")) {
                reference.setId(value);
            }else
                reference.setRefAttribute(_field, value);
        }
    }

    /**
     * Gets ref attribute.
     *
     * @param field the field
     * @return the ref attribute
     */
    public Object getRefAttribute(String field) {
        int index = field.indexOf('.');
        if(index < 0) {
            return this.getAttribute(field);
        }else {
            String ref = field.substring(0, index);
            String _field = field.substring(index +1);
            Tuple reference = parent.get(ref);
            if(null == reference) {
                reference = new Tuple();
                parent.put(ref, reference);
            }
            if(_field.equals("id")) {
                return reference.getId();
            }else
                return reference.getRefAttribute(_field);
        }
    }

    /**
     * @param value
     */
    private void setId(Object value) {
        if(null == value)
            this.id = null;
        else if(value instanceof String)
            this.id = (String) value;
        else
            this.id = value.toString();

    }
}
