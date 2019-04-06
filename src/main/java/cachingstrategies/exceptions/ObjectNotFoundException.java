package cachingstrategies.exceptions;

public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = 3338595474250887243L;

	public ObjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }
	
}
