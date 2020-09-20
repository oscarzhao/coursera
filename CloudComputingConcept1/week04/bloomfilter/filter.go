package bloomfilter

import (
	"fmt"
	"sync"
)

// BloomFilter defines methods for a Bloom Filter
type BloomFilter interface {
	Insert(x int64)
	Exists(x int64) bool
}

// NewBloomFilter returns a default bloom filter object
func NewBloomFilter(m int, hashfuncs ...func(int64) int) BloomFilter {
	return &defaultBloomFilter{
		data:      make([]bool, m, m),
		hashfuncs: hashfuncs,
	}
}

type defaultBloomFilter struct {
	sync.RWMutex
	data      []bool
	hashfuncs []func(int64) int
}

func (bf *defaultBloomFilter) Insert(x int64) {
	bf.Lock()
	defer bf.Unlock()
	for _, f := range bf.hashfuncs {
		idx := f(x)
		// to debug question 22
		// if bf.data[idx] == false {
		// 	fmt.Printf("changed bit %d to true\n", idx)
		// }
		bf.data[idx] = true
	}
}

func (bf *defaultBloomFilter) Exists(x int64) bool {
	bf.RLock()
	defer bf.RUnlock()
	for _, f := range bf.hashfuncs {
		if !bf.data[f(x)] {
			return false
		}
	}
	return true
}

func (bf *defaultBloomFilter) printBits(trueOnly bool) {
	bf.RLock()
	defer bf.RUnlock()
	for idx, val := range bf.data {
		if trueOnly && (val == false) {
			continue
		}
		fmt.Printf("%2d %v\n", idx, val)
	}
}
