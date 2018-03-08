package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.Term
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}
import Characters._
import com.github.skozlov.lamca.parser.Messages._

class BracketsParser extends BlockParser[Term] {
	@throws[ParsingException]
	override def apply(context: ParsingContext): ParsingResult[Term] = {
		require(
			context.currentChar contains OpeningBracket,
			s"Required $OpeningBracket. Position: ${context.currentPosition}."
		)
		bodyParser(context.rightShift) match {
			case ParsingResult(term, finalContext) => finalContext.currentChar match {
				case None => throw ParsingException(missingCharacter(ClosingBracket), finalContext.currentPosition)
				case Some(char) =>
					if (char != ClosingBracket){
						throw ParsingException(
							unexpectedCharacter(expected = ClosingBracket),
							finalContext.currentPosition
						)
					}
					ParsingResult(term, finalContext.rightShift)
			}
		}
	}

	protected def bodyParser: TermParser = TermParser
}

object BracketsParser extends BracketsParser