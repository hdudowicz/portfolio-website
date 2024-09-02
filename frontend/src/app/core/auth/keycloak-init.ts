import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080/',
        realm: 'master',
        clientId: 'web-app',
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe: false,
        redirectUri: window.location.origin + '/home'
        // silentCheckSsoRedirectUri:
        //   window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}
