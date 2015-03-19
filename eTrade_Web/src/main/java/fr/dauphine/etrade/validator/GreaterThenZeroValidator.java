package fr.dauphine.etrade.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("positiveNumberValidator")
public final class GreaterThenZeroValidator implements
		javax.faces.validator.Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		try {
			if (Integer.parseInt(value.toString()) < 1) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation failed.",
						"Number must be strictly positive");
				throw new ValidatorException(msg);
			}
		} catch (NumberFormatException ex) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation failed.",
					"Not an integer");
			throw new ValidatorException(msg);
		}
	}

}
