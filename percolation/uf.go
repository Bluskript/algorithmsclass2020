package main

type UF struct {
	id   []int
	size []int
}

func NewUF(N int, numRows int) UF {
	u := UF{
		id:   make([]int, N),
		size: make([]int, N),
	}
	for i := 0; i < N; i++ {
		row := i / numRows
		if row == 0 {
			u.id[i] = 0
		} else if row == numRows-1 {
			u.id[i] = row * numRows
		} else {
			u.id[i] = i
		}
		u.size[i] = 1
	}
	return u
}

func (u *UF) root(p int) int {
	for p != u.id[p] {
		p = u.id[p]
	}
	return p
}

func (u *UF) Union(p, q int) {
	i := u.root(p)
	j := u.root(q)

	u.id[i] = j
}

func (u *UF) Find(p, q int) bool {
	return u.root(p) == u.root(q)
}
