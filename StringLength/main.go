package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"sort"
	"strings"
)

func main() {
	targetFile := os.Args[1]
	raw, _ := ioutil.ReadFile(targetFile)
	content := string(raw)
	split := strings.Split(content, "\n")
	sort.Slice(split, func(i, j int) bool {
		return len(split[i]) < len(split[j])
	})
	fmt.Println(strings.Join(split, "\n"))
}
