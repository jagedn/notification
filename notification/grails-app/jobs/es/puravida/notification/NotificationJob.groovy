package es.puravida.notification



class NotificationJob {
	
	def concurrent = false
	
	static triggers = {
      simple name: 'notificationTrigger', startDelay: 6000, repeatInterval: 60000l // execute job once in 60 seconds
    }
	
	def notificationService

    def execute() {
        notificationService.sendPending() 
    }

}
