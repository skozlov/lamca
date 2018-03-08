package com.github.skozlov.lamca.parser

package object block{
	type BlockParser[+T] = ParsingContext => ParsingResult[T]
}