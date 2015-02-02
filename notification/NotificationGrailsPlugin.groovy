import es.puravida.notification.NotificationService;

class NotificationGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

	def dependsOn = [mail: "1.0.7 > *", quartz2: "2.1.6.2  > *"]
	
    // TODO Fill in these fields
    def title = "Notification Plugin" // Headline display name of the plugin
    def author = "Jorge Aguilera"
    def authorEmail = "jorge.aguilera@puravida-software.com"
    def description = '''\
Async email to admin, users or external customer with a custom view.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/notification"	
	
    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }
		
    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
		
		for (controllerClass in application.controllerClasses) {
			controllerClass.metaClass.prepareNotification = { Closure dsl ->
				ctx.notificationService.prepareNotificationWithClosure(dsl)
			}
	   }
		for (serviceClass in application.serviceClasses) {			
			serviceClass.metaClass.prepareNotification = { Closure dsl ->
				ctx.notificationService.prepareNotificationWithClosure(dsl)
			}
	   }
		for (bootstrapClass in application.bootstrapClasses) {
			bootstrapClass.metaClass.prepareNotification = { Closure dsl ->
				ctx.notificationService.prepareNotificationWithClosure(dsl)
			}
	   }

    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
