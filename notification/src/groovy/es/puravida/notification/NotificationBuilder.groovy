package es.puravida.notification

import grails.converters.JSON;

class NotificationBuilder {
	
	def notification
		
	public NotificationBuilder(){
		notification = new Notification()
	}
	
	void title(String str){
		notification.title = str
	}
	
	void toAdmin(boolean toadmin){
		notification.toAdmin = toadmin
	}
	
	void message(String str){
		notification.message = str
	}
	
	void model(Map model){
		notification.message = (model as JSON)
	}
	
	void email(String email){
		notification.email = email
	}
			
	void template( String template, String plugin=null ){
		notification.template = template
		notification.plugin = plugin
	}
	
	void attachDocument( int idx, String path){
		idx = idx < 1 ? 1 : idx;
		idx = idx > 4 ? 4 : idx;
		notification."attachDocument${idx}" = path
	}

}
