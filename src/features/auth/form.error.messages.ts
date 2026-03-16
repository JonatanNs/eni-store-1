export const FORM_ERROR_MESSAGES: { [controlName: string]: { [errorType: string]: string } } = {
  firstName: {
    required: "Le nom est requis.",
    condition : "Le nom n'est pas valide."
  },
  lastName: {
    required: "Le prénom est requis.",
    condition : "Le prénom n'est pas valide."
  },
    email: {
    required: "Email requis",
    email: "Format mail invalide",
    exist : "Email existe déja connecté vous !"
  },
  password: {
    required: "Mot de passe requis",
    pattern: "Le mot de passe doit contenir au moins 12 caractères, avec une majuscule, une minuscule, un chiffre et un caractère spécial.",
  },
  confirmPassword: {
    required: "Mot de passe requis",
    passwordMismatch: "Les mots de passe ne correspondent pas"
  }
};