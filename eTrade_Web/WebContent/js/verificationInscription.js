/**
 * This function overline the background in red if there is an error.
 * 
 * @param champ
 * @param erreur
 */
function surligne(champ, erreur) {
	if (erreur) {
		champ.style.backgroundColor = "#fba";
	} else {
		champ.style.backgroundColor = "";
	}
}

/**
 * This function checks if the mail adress is well structured.
 * 
 * @param champ
 */
function verifMail(champ) {
	var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	if (!regex.test(champ.value)) {
		surligne(champ, true);
		return false;
	} else {
		surligne(champ, false);
		return true;
	}
}

/**
 * This function checks if the given pseudo could be accepted or not.
 * 
 * @param champ
 * @returns {Boolean}
 */
function verifPseudo(champ) {
	if (champ.value.length < 2 || champ.value.length > 40)
	// Corresponding to
	// the 40 caracters
	// in the database
	{
		surligne(champ, true);
		return false;
	} else {
		surligne(champ, false);
		return true;
	}
}

/**
 * This function checks if the password is more than 8 caracters.
 * 
 * @param champ
 * @returns {Boolean}
 */
function verifPassword(champ) {
	if (champ.value.length < 8) {
		surligne(champ, true);
		return false;
	} else {
		surligne(champ, false);
		return true;
	}
}

/**
 * This function checks if the confirmation password has the same value than the
 * initial one.
 * 
 * @param champ
 * @returns {Boolean}
 */
function verifConfirmPassword(champ) {

	if (champ.value != document.getElementById("password").value) {
		surligne(champ, true);
		return false;
	} else {
		surligne(champ, false);
		return true;
	}
}

/**
 * This functions checks if all the conditions are respected to submit the form.
 * 
 * @param f
 * @returns {Boolean}
 */
function verifForm(f) {
	var pseudoOk = verifPseudo(f.Utilisateurname);
	var mailOk = verifMail(f.mail);
	var passwordOk = verifPassword(f.password);
	var passwordConfirmOk = verifConfirmPassword(f.confirmPassword);

	if (pseudoOk && mailOk && passwordOk && passwordConfirmOk)
		return true;
	else {
		alert("Merci de remplir correctement tous les champs");
		return false;
	}
}
/**
 * Disables the given input
 * 
 * @param champ
 *            the input to disable for the user
 */
function disableIt(champ) {
	document.getElementById(champ).disabled = true;
}
