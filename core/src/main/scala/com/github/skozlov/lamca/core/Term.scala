package com.github.skozlov.lamca.core

sealed trait Term

case class Variable(name: String) extends Term

case class Application(function: Term, argument: Term) extends Term

case class Abstraction(variable: Variable, body: Term) extends Term

object Abstraction{
	def apply(body: Term, variable1: Variable, otherVariables: Variable*): Abstraction = {
		val variableReversed = (variable1 +: otherVariables).reverse
		variableReversed.tail.foldLeft(Abstraction(variableReversed.head, body)){
			(body, variable) => Abstraction(variable, body)
		}
	}
}