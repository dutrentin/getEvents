package br.com.poc.enuns;

public enum TypeDistanceEnum {

    LATITUDE(0),
    LONGITUDE(1);

    private int valueType;

    TypeDistanceEnum(int value){
        this.valueType = value;
    }

    public int getValueType(){
        return valueType;
    }
}
