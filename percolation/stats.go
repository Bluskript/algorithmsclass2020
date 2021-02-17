package main

import (
	"math"
	"math/rand"
)

type PercolationStats struct {
	mean           float64
	stddev         float64
	confidenceLow  float64
	confidenceHigh float64
}

func NewPercolationStats(n, t int) PercolationStats {
	stats := PercolationStats{}

	sum := 0.0
	trials := make([]float64, t)

	for i := 0; i < t; i++ {
		p := NewPercolation(n)
		for !p.Percolates() {
			p.Open(rand.Intn(n), rand.Intn(n))
		}
		sum += float64(p.NumberOfOpenSites()) / float64(n*n)
		trials[i] = float64(p.NumberOfOpenSites()) / float64(n*n)
	}

	stats.mean = float64(sum) / float64(t)
	stats.stddev = 0

	for _, num := range trials {
		stats.stddev += math.Pow(float64(num)-stats.mean, 2)
	}

	stats.stddev = math.Sqrt(stats.stddev / float64(t))

	confInterval := 0.95 * (stats.stddev / math.Sqrt(float64(t)))

	stats.confidenceHigh = stats.mean + confInterval
	stats.confidenceLow = stats.mean - confInterval

	return stats
}
