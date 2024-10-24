package main
import (
	"fmt"
)

func isOdd(n int) bool {
	if n % 2 == 1 {
		return true
	} else {
		return false
	}
}

func main() {
	for i := -5; i <= 5; i++ {
		fmt.Println(i, isOdd(i))
	}
}