public class TypeErrorException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String error;

    public TypeErrorException(String message){
        this.error = message;
    }

    public String getMessage() {
        return error;
    }
}

