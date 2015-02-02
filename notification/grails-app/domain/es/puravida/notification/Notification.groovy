package es.puravida.notification

import java.util.Date;

class Notification {

    static constraints = {
		dateNotificated nullable:true 
		attachDocument1 nullable:true
		attachDocument2 nullable:true
		attachDocument3 nullable:true
		attachDocument4 nullable:true
		
		plugin	nullable:true
		template nullable:true
		email	nullable:true
    }
	
	Date			dateCreated
	Date			dateNotificated
	
	boolean			toAdmin = true
	String			email
	
	String			title
	String			plugin
	String			template
	String			message
	String			attachDocument1
	String			attachDocument2
	String			attachDocument3
	String			attachDocument4

}
