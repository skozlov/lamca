package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.Abstraction
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}

class AbstractionParser extends BlockParser[Abstraction] {
	@throws[ParsingException]
	override def apply(context: ParsingContext): ParsingResult[Abstraction] = {
		require(
			context.currentChar contains Characters.AbstractionStart,
			s"Required ${Characters.AbstractionStart}. Position: ${context.currentPosition}."
		)
		variablesParser(context) match {
			case ParsingResult(variables, bodyContext) => bodyParser(bodyContext) match {
				case ParsingResult(body, finalContext) => ParsingResult(
					Abstraction(variables, body),
					finalContext
				)
			}
		}
	}

	protected def variablesParser: AbstractionVariablesParser = AbstractionVariablesParser

	protected def bodyParser: TermParser = TermParser
}

object AbstractionParser extends AbstractionParser