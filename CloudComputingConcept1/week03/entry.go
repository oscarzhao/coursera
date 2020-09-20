package week03

import (
	"sort"
)

// successors returns all successors for a certain value within a ring table
// Time cost: O(n), space cost: O(n), n = len(in)
func successors(m int, in []int, x int) []int {
	if !sort.IntsAreSorted(in) {
		panic("input array must be sorted in increasing order")
	}
	lenIn := len(in)

	pivot := sort.IntSlice(in).Search(x)
	if pivot == lenIn || in[pivot] != x {
		panic("x is not in array in")
	}
	modBy := 1 << uint(m)
	in2x := append(in, in...)
	for i := lenIn; i < 2*lenIn; i++ {
		in2x[i] += modBy
	}

	var res []int
	k := pivot + 1
	for i := 0; i < m; i++ {
		val := x + 1<<uint(i)
		for val > in2x[k] {
			k++
		}
		res = append(res, in2x[k]%modBy)
	}
	return res
}

// predecessors returns all predecessors for a certain value within a ring table
// Time cost: O(n) * O(log(m)), space cost: O(n)
func predecessors(m int, in []int, x int) []int {
	if !sort.IntsAreSorted(in) {
		panic("input array must be sorted in increasing order")
	}
	lenIn := len(in)

	pivot := sort.IntSlice(in).Search(x)
	if pivot == lenIn || in[pivot] != x {
		panic("x is not in array in")
	}

	modBy := 1 << uint(m)
	in2x := append(in, in...)
	for i := lenIn; i < 2*lenIn; i++ {
		in2x[i] += modBy
	}

	diffSlice := make([]int, m)
	for i := 0; i < m; i++ {
		diffSlice[i] = 1 << uint(i)
	}

	newX := x + modBy
	newPivot := pivot + lenIn
	var res []int
	for k := pivot + 1; k < newPivot; k++ {
		diff := newX - in2x[k]
		idx := sort.SearchInts(diffSlice, diff)
		if idx < m && diffSlice[idx] == diff {
			res = append(res, in2x[k]%modBy)
			continue
		}
		tmpVal := in2x[k] + diffSlice[idx-1]
		if tmpVal > in2x[newPivot-1] { // the in[pivot-1] is not a better candidate for in[k]'s successor
			res = append(res, in2x[k]%modBy)
		}
	}

	// sort the result
	lenRes := len(res)
	cut := 1
	for cut < lenRes {
		if res[cut-1] > res[cut] {
			break
		}
	}
	if cut == lenRes {
		return res
	}
	return append(res[cut:], res[:cut]...)
}
