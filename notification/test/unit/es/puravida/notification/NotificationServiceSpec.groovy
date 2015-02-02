
package es.puravida.notification

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(NotificationService)
@Mock(Notification)
class NotificationServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test prepareNotification"() {
		given:
			def dsl = {
				title "Aplicacion iniciada"
				toAdmin true
				message "Aplicacion SON Iniciada ${new Date()}"
			}
			
		when:
			service.prepareNotificationWithClosure dsl
			
		then:
			true
    }
}
