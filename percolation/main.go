package main

import (
	"fmt"
	"time"
)

func main() {
	n := 4
	fmt.Println("===START N BENCH===")
	for i := 0; i < 8; i++ {
		start := time.Now()
		_ = NewPercolationStats(n, 1)
		fmt.Println(time.Now().Sub(start).Nanoseconds())
		n *= 2
	}
	fmt.Println("===START T BENCH===")
	t := 1
	for i := 0; i < 8; i++ {
		start := time.Now()
		_ = NewPercolationStats(100, t)
		fmt.Println(time.Now().Sub(start).Nanoseconds())
		t *= 2
	}
}
