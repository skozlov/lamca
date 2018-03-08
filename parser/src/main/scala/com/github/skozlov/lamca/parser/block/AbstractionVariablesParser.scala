package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.core.Variable
import com.github.skozlov.lamca.parser.Messages._
import com.github.skozlov.lamca.parser.block.Characters._
import com.github.skozlov.lamca.parser.{ParsingContext, ParsingException}

import scala.annotation.tailrec

class AbstractionVariablesParser extends BlockParser[List[Variable]] {

	@throws[ParsingException]
	override def apply(context: ParsingContext): ParsingResult[List[Variable]] = {
		@tailrec
		def parse(context: ParsingContext, parsedReverse: List[Variable]): ParsingResult[List[Variable]] = {
			if (context.isCompleted){
				if (parsedReverse.isEmpty){
					throw ParsingException(MissingAbstractionVariables, context.currentPosition)
				} else {
					throw ParsingException(MissingAbstractionBody, context.currentPosition)
				}
			} else context.currentChar match {
				case None => parse(context.toNextLine, parsedReverse)
				case Some(char) =>
					if (char.isWhitespace){
						parse(context.rightShift, parsedReverse)
					} else if (canBeFirstInVariable(char)){
						variableParser(context) match {
							case ParsingResult(variable, nextContext) => parse(nextContext, variable :: parsedReverse)
						}
					} else if (parsedReverse.isEmpty) {
						throw ParsingException(MissingAbstractionVariables, context.currentPosition)
					} else if (char == AbstractionVariablesAndBodyDelimiter) {
						ParsingResult(parsedReverse.reverse, context.rightShift)
					} else {
						throw ParsingException(MissingAbstractionBody, context.currentPosition)
					}
			}
		}

		parse(context, Nil)
	}

	protected def variableParser: VariableParser = VariableParser
}

object AbstractionVariablesParser extends AbstractionVariablesParser