package main

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"sort"
	"strings"
)

type Entry struct {
	name      string
	telephone string
}

func main() {
	targetFile := os.Args[1]
	raw, _ := ioutil.ReadFile(targetFile)
	content := string(raw)
	split := strings.Split(content, "\n")
	byName := make([]Entry, len(split))
	byPhone := make([]Entry, len(split))

	for i, line := range split {
		entry := strings.Split(line, ",")
		if len(entry) == 2 {
			e := Entry{name: entry[0], telephone: entry[1]}
			byName[i] = e
			byPhone[i] = e
		}
	}

	sort.Slice(byName, func(i, j int) bool {
		return strings.Compare(byName[i].name, byName[j].name) < 0
	})
	sort.Slice(byPhone, func(i, j int) bool {
		return strings.Compare(byPhone[i].telephone, byPhone[j].telephone) < 0
	})

	sc := bufio.NewScanner(os.Stdin)
	fmt.Println(`Do "telephone <phone>" or "name <name>" to lookup`)
	for sc.Scan() {
		in := sc.Text()
		if strings.HasPrefix(in, "telephone ") {
			phone := strings.TrimPrefix(in, "telephone ")
			idx := sort.Search(len(byPhone), func(i int) bool {
				return strings.Compare(byPhone[i].telephone, phone) >= 0
			})
			fmt.Println(byPhone[idx].name)
		} else {
			name := strings.TrimPrefix(in, "name ")
			idx := sort.Search(len(byPhone), func(i int) bool {
				return strings.Compare(byName[i].name, name) >= 0
			})
			fmt.Println(byName[idx].telephone)
		}
	}
}
