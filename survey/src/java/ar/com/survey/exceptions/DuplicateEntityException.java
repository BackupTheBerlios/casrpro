package ar.com.survey.exceptions;


public class DuplicateEntityException extends BusinessException {
	
	transient Object transientEntity;
	transient Object existingEntity;
	
	
	private static final long serialVersionUID = 1L;

	public DuplicateEntityException() {
		super();
	}

	public DuplicateEntityException(String message) {
		super(message);
	}

	public DuplicateEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEntityException(Throwable cause) {
		super(cause);
	}

	public Object getExistingEntity() {
		return existingEntity;
	}

	public void setExistingEntity(Object existingEntity) {
		this.existingEntity = existingEntity;
	}

	public Object getTransientEntity() {
		return transientEntity;
	}

	public void setTransientEntity(Object transientEntity) {
		this.transientEntity = transientEntity;
	}
}
