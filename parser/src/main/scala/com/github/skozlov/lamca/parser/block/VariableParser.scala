package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.Variable
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}
import Characters._
import scala.annotation.tailrec

class VariableParser extends BlockParser[Variable] {

	@throws[ParsingException]
	override def apply(context: ParsingContext): ParsingResult[Variable] = {
		context.currentChar match {
			case None => throw new IllegalArgumentException(
				s"No characters left on the current line. Position: ${context.currentPosition}"
			)
			case Some(firstChar) =>
				val line = context.currentLine.get
				val charIndex = context.currentPosition.char
				val charsLeft = line.length - charIndex
				val name = new StringBuilder(capacity = charsLeft)
				require(
					canBeFirstInVariable(firstChar),
					s"Invalid first char of variable: $firstChar. Position: ${context.currentPosition}."
				)
				name += firstChar

				@tailrec
				def parseRest(context: ParsingContext): ParsingResult[Variable] = {
					context.currentChar match {
						case None => ParsingResult(Variable(name.toString()), context.toNextLine)
						case Some(char) =>
							if (isValidInVariable(char)){
								name += firstChar
								parseRest(context.rightShift)
							} else {
								ParsingResult(Variable(name.toString()), context)
							}
					}
				}

				parseRest(context.rightShift)
		}
	}
}

object VariableParser extends VariableParser