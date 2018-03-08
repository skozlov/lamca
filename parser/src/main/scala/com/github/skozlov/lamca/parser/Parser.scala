package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.core.Term

class Parser extends (List[String] => Term) {
	@throws[ParsingException]
	def apply(sourceLines: List[String]): Term = ???

	val parse: List[String] => Term = this
}