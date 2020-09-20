package bloomfilter

import (
	"testing"

	"github.com/stretchr/testify/require"
)

/*
	Question 22: Your boss loves Bloom filters. To impress her, you start implementing one.
		Your Bloom filter uses m=32 bits and 3 hash functions h1, h2, and h3, where hi(x) = ((x2 +x3)*i) mod m.
		In this case, answer the following question. (1 point)

	Starting from an empty Bloom filter, you’ve inserted the following three elements: 2010, 2013, 2007.
	Now some bits are set. Now, you insert the fourth element 2004. Note the bits in the Bloom filter have positions numbered 0 through 31.
	The bits whose values will change when the fourth element 2004 is inserted INCLUDE:

	should be zero.
*/
func TestBloomFilter_Q22(t *testing.T) {
	bf := NewBloomFilter(32, defaultHashfuncGenerator(1, 32), defaultHashfuncGenerator(2, 32), defaultHashfuncGenerator(3, 32))
	bf.Insert(2007)
	bf.Insert(2010)
	bf.Insert(2013)

	// fmt.Printf(".............insert 2004....\n") // question 22
	bf.Insert(2004)

	require.True(t, bf.Exists(2004))
	require.True(t, bf.Exists(2007))
	require.True(t, bf.Exists(2010))
	require.True(t, bf.Exists(2013))
}

/* Starting from an empty Bloom filter,
you’ve inserted the following elements: 2013, 2010, 2007, 2004, 2001, 1998.
If someone checks for membership of the element 0, it will be found to be:
*/
func TestBloomFilter_Q23_v2(t *testing.T) {
	bf := NewBloomFilter(32, defaultHashfuncGenerator(1, 32), defaultHashfuncGenerator(2, 32), defaultHashfuncGenerator(3, 32))
	bf.Insert(2007)
	bf.Insert(2010)
	bf.Insert(2013)
	bf.Insert(2004)
	bf.Insert(2001)
	bf.Insert(1998)

	require.True(t, bf.Exists(0)) // it's false positive
}

func TestBloomFilter_Q24(t *testing.T) {
	bf := NewBloomFilter(32, defaultHashfuncGenerator(1, 32), defaultHashfuncGenerator(2, 32), defaultHashfuncGenerator(3, 32))
	bf.Insert(2007)
	bf.Insert(2010)
	bf.Insert(2013)
	bf.Insert(2004)

	require.True(t, bf.Exists(3200)) // it's false positive
}

func TestBloomFilter_printBits(t *testing.T) {
	bf := &defaultBloomFilter{
		data: make([]bool, 32, 32),
		hashfuncs: []func(int64) int{
			defaultHashfuncGenerator(1, 32),
			defaultHashfuncGenerator(2, 32),
			defaultHashfuncGenerator(3, 32),
		},
	}

	bf.Insert(2007)
	bf.Insert(2010)
	bf.Insert(2013)

	bf.printBits(true)
}
