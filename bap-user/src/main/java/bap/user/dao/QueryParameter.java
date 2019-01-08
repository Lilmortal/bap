package bap.user.dao;

public class QueryParameter<T extends Object> {
    private int parameterIndex;

    private T parameter;

    public QueryParameter(int parameterIndex, T parameter) {
        this.parameterIndex = parameterIndex;
        this.parameter = parameter;
    }

    public int getParameterIndex() {
        return parameterIndex;
    }

    public T getParameter() {
        return parameter;
    }
}
