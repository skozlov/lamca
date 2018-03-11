package com.github.skozlov.lamca.core

sealed trait Term

case class Variable(name: String) extends Term

case class Application(function: Term, argument: Term) extends Term

object Application{
	def apply(operands: Seq[Term]): Application = {
		require(operands.lengthCompare(2) >= 0, s"Required at least 2 operands, but found ${operands.size} ones.")
		(operands drop 2).foldLeft(Application(operands.head, operands(1))){
			(function, argument) => Application(function, argument)
		}
	}

	def apply(operand1: Term, operand2: Term, operand3: Term, otherOperands: Term*): Application =
		Application(operand1 +: operand2 +: operand3 +: otherOperands)
}

case class Abstraction(variable: Variable, body: Term) extends Term

object Abstraction{
	def apply(variables: Seq[Variable], body: Term): Abstraction = {
		require(variables.nonEmpty, "No variables specified")
		val variablesReversed = variables.reverse
		variablesReversed.tail.foldLeft(Abstraction(variablesReversed.head, body)){
			(body, variable) => Abstraction(variable, body)
		}
	}

	def apply(body: Term, variable1: Variable, otherVariables: Variable*): Abstraction =
		Abstraction(variable1 +: otherVariables, body)
}