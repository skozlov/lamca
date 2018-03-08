package com.github.skozlov.lamca.core

import com.github.skozlov.lamca.core.Message._
import org.apache.commons.text.StrSubstitutor

import scala.collection.JavaConverters

case class Message(key: String, defaultTemplate: String, parameters: Parameters = Parameters.Empty){
	def resolved: String = new StrSubstitutor(JavaConverters.mapAsJavaMap(parameters)).replace(defaultTemplate)
}

object Message{
	type Parameters = Map[String, _]

	object Parameters{
		val Empty: Parameters = Map.empty
	}
}