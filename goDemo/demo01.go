package main

import (
	"fmt"
	"os"
)

func main()  {
	s, sep :="",""
	for index, arg := range  os.Args[0:]{
		s += sep + arg
		sep =" "
		fmt.Println(arg)
		fmt.Println(index)
	}

}


