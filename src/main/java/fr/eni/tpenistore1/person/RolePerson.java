package fr.eni.tpenistore1.person;

/**
 * Classe 'RoleUser' en charge d'énumérer les différents roles que peut avoir un utilisateur.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:11
 */
public enum RolePerson {
    USER,
    ADMIN,
    ADMIN_SUPER;

    /**
     * Retourne le nom complet du rôle sous la forme utilisée par Spring Security.
     * Cette méthode préfixe le nom de l’énumération par "ROLE_",
     * conformément aux conventions de Spring Security pour les rôles.
     *
     * @return le nom complet du rôle sous la forme "ROLE_<NOM_DU_ROLE>"
     */
    public String getName() {
        return "ROLE_" + this.name();
    }
}
