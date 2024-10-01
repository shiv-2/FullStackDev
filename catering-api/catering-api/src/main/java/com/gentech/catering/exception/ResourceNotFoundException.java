package com.gentech.catering.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String fieldValue;
    private Object valueOf;

    public ResourceNotFoundException(){

    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public Object getValueOf() {
        return valueOf;
    }

    public ResourceNotFoundException(String resourceName, String fieldValue, Object valueOf){
        super(String.format("Resource named:'%s' with '%s' does not have '%s'",resourceName,fieldValue,valueOf));
        this.resourceName=resourceName;
        this.fieldValue=fieldValue;
        this.valueOf=valueOf;
    }
}
