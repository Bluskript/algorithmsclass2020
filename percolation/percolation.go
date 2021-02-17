package main

import "fmt"

type Percolation struct {
	uf        UF
	grid      []bool
	openCount int
	size      int
}

func NewPercolation(N int) Percolation {
	p := Percolation{
		uf:        NewUF(N*N, N),
		grid:      make([]bool, N*N),
		openCount: 0,
		size:      N,
	}

	return p
}

func (p *Percolation) getIdx(x, y int) int {
	return y*p.size + x
}

func (p *Percolation) getY(idx int) int {
	return idx / p.size
}

func (p *Percolation) getX(idx int) int {
	return idx % p.size
}

func (p *Percolation) Open(x, y int) {
	if !p.IsOpen(x, y) {
		p.openCount++
		if x != 0 && p.IsOpen(x-1, y) {
			p.uf.Union(p.getIdx(x, y), p.getIdx(x-1, y))
		}
		if x != p.size-1 && p.IsOpen(x+1, y) {
			p.uf.Union(p.getIdx(x, y), p.getIdx(x+1, y))
		}
		if y != 0 && p.IsOpen(x, y-1) {
			p.uf.Union(p.getIdx(x, y), p.getIdx(x, y-1))
		}
		if y != p.size-1 && p.IsOpen(x, y+1) {
			p.uf.Union(p.getIdx(x, y), p.getIdx(x, y+1))
		}
	}
	p.grid[p.getIdx(x, y)] = true
}

func (p *Percolation) IsOpen(x, y int) bool {
	return p.grid[p.getIdx(x, y)]
}

func (p *Percolation) IsFull(x, y int) bool {
	return p.uf.Find(p.getIdx(x, y), 0)
}

func (p *Percolation) NumberOfOpenSites() int {
	return p.openCount
}

func (p *Percolation) Percolates() bool {
	return p.uf.Find(p.getIdx(0, 0), p.getIdx(0, p.size-1))
}

func (p *Percolation) String() string {
	s := ""
	for i := 0; i < p.size; i++ {
		for j := 0; j < p.size; j++ {
			s += fmt.Sprintf("%-2d ", p.uf.root(p.uf.id[p.getIdx(j, i)]))
		}
		s += "\n"
	}
	return s
}
