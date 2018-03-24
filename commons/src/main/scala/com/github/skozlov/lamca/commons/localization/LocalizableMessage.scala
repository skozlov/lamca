package com.github.skozlov.lamca.commons.localization

import LocalizableMessage._
import org.apache.commons.text.StrSubstitutor

import scala.collection.JavaConverters

case class LocalizableMessage(key: String, defaultTemplate: String, parameters: Parameters = Parameters.Empty){
	def resolved(template: String = defaultTemplate): String =
		new StrSubstitutor(JavaConverters.mapAsJavaMap(parameters)).replace(defaultTemplate)
}

object LocalizableMessage{
	type Parameters = Map[String, _]

	object Parameters{
		val Empty: Parameters = Map.empty
	}
}