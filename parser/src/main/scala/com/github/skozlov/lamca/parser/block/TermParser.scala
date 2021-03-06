package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.{Application, Term}
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}
import Characters._

import scala.annotation.tailrec
import com.github.skozlov.lamca.parser.Messages._
import com.github.skozlov.lamca.parser.sourcePosition.SourcePosition

class TermParser extends BlockParser[Term] {
	@throws[ParsingException]
	override def apply(context: ParsingContext): ParsingResult[Term] = {
		val startContext = context

		@tailrec
		def parse(context: ParsingContext, termsReversed: List[Term]): ParsingResult[Term] = {
			lazy val onCompletion = {
				val term = {
					if (termsReversed.isEmpty){
						val endPosition = context.currentPosition match {
							case position @ SourcePosition(0, 0) => position
							case SourcePosition(line, 0) =>
								val previousLine = startContext.sourceLines(line - startContext.currentPosition.line)
								SourcePosition(line = line - 1, charIndex = previousLine.length - 1)
							case position @ SourcePosition(_, char) => position.copy(charIndex = char - 1)
						}
						throw ParsingException(
							EmptyTerm,
							from = startContext.currentPosition,
							to = endPosition
						)
					} else if (termsReversed.lengthCompare(1) == 0){
						termsReversed.head
					} else {
						Application(termsReversed.reverse)
					}
				}
				ParsingResult(term, context)
			}

			if (context.isCompleted){
				onCompletion
			} else context.currentChar match {
				case None => parse(context.toNextLine, termsReversed)
				case Some(char) =>
					if (char.isWhitespace){
						parse(context.toNextLine, termsReversed)
					} else {
						val subTermParser = char match {
							case OpeningBracket => Some(bracketsParser)
							case AbstractionStart => Some(abstractionParser)
							case _ if canBeFirstInVariable(char) => Some(variableParser)
							case _ => None
						}
						subTermParser match {
							case None => onCompletion
							case Some(parser) => parser(context) match {
								case ParsingResult(term, finalContext) => parse(finalContext, term :: termsReversed)
							}
						}
					}
			}
		}

		parse(context, Nil)
	}

	protected def bracketsParser: BracketsParser = BracketsParser

	protected def abstractionParser: AbstractionParser = AbstractionParser

	protected def variableParser: VariableParser = VariableParser
}

object TermParser extends TermParser