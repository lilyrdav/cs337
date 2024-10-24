package main
import "fmt"

func isOdd(n int) bool {
	return ((n & 1) != 0)
}

func main() {
	for i := -5; i <= 5; i++ {
		fmt.Println(i, isOdd(i))
	}
}