package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.{Application, Term}
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}
import Characters._

import scala.annotation.tailrec
import com.github.skozlov.lamca.parser.Messages._
import com.github.skozlov.lamca.parser.sourceCoordinate.SourceCoordinate

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
							case position @ SourceCoordinate(0, 0) => position
							case SourceCoordinate(line, 0) =>
								val previousLine = startContext.sourceLines(line - startContext.currentPosition.line)
								SourceCoordinate(line = line - 1, char = previousLine.length - 1)
							case position @ SourceCoordinate(_, char) => position.copy(char = char - 1)
						}
						throw ParsingException(
							EmptyTerm,
							from = startContext.currentPosition,
							to = endPosition
						)
					} else {
						val terms = termsReversed.reverse
						terms.tail.foldLeft(terms.head){(left, right) => Application(function = left, argument = right)}
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