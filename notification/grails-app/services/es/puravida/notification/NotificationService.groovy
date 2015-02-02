package es.puravida.notification

import grails.transaction.Transactional
import grails.validation.ValidationException;

import java.nio.file.Files;

import groovy.json.JsonSlurper;
import grails.converters.JSON;

@Transactional
class NotificationService {

	def grailsApplication

	def prepareNotificationWithClosure( Closure callable ){
		NotificationBuilder builder = new NotificationBuilder()
		callable.delegate = builder
		callable.resolveStrategy = Closure.DELEGATE_FIRST
		callable.call(builder)

		def n = builder.notification
		if (!n.validate()) {
			throw new ValidationException("Notification is not valid", n.errors)
		}
		n.save(flush:true)
	}

	def sendPending() {
		def n = Notification.findWhere(dateNotificated:null)
		if(n != null){
			sendNotification( n )
			n.dateNotificated = new Date()
			n.save(flush:true)
		}
	}

	def sendNotification( Notification n ){
		String adminEmail = grailsApplication.config?.puravida?.notification?.adminEmail
		String fromEmail = grailsApplication.config?.puravida?.notification?.fromEmail

		String[]email = []
		if( n.toAdmin )
			email = adminEmail  ? adminEmail.split(";") : []
		else
			email = "$n.email".split(";")
		email = email.findAll{ it != null && it.indexOf('@') != -1}

		if( fromEmail && fromEmail != "" && email.size()  ){
			try{
				def slurper = new JsonSlurper()
				sendMail{
					multipart true
					to email
					from fromEmail
					subject n.title
					if( n.template ){
						def model = n.message ? slurper.parseText(n.message) : [:]
						html ( plugin:n.plugin, view:n.template, model:model )
					}else{
						body n.message
					}
					(1..4).each{
						if( n."attachDocument$it"){
							File f = new File(n."attachDocument$it")
							if(f.exists())
								attachBytes f.name,URLConnection.fileNameMap.getContentTypeFor(f.absolutePath), f.readBytes()
						}
					}
				}
			}catch(e){
				println e
			}
		}
	}
}
