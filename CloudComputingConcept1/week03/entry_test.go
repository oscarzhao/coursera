package week03

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/require"
)

func TestSuccessors(t *testing.T) {
	testcases := []struct {
		desc string
		m    int
		in   []int
		x    int

		out []int
	}{
		{
			m:  9,
			in: []int{1, 12, 123, 234, 345, 456, 501},
			x:  234,

			out: []int{345, 345, 345, 345, 345, 345, 345, 456, 501},
		},
		{
			m:  9,
			in: []int{1, 12, 123, 234, 345, 456, 501},
			x:  501,

			out: []int{1, 1, 1, 1, 12, 123, 123, 123, 345},
		},
		{
			m:  9,
			in: []int{1, 12, 123, 234, 345, 456, 501},
			x:  1,

			out: []int{12, 12, 12, 12, 123, 123, 123, 234, 345},
		},
		{
			desc: "search 12, choose 199 among the out",
			m:    8,
			in:   []int{32, 45, 99, 132, 199, 234},
			x:    45,

			out: []int{99, 99, 99, 99, 99, 99, 132, 199},
		},
		{
			desc: "search 12, choose 234 among the out",
			m:    8,
			in:   []int{32, 45, 99, 132, 199, 234},
			x:    199,

			out: []int{234, 234, 234, 234, 234, 234, 32, 99},
		},
		{
			m:  7,
			in: []int{16, 32, 45, 80, 96, 112},
			x:  80,

			out: []int{96, 96, 96, 96, 96, 112, 16},
		},
		{
			m:  7,
			in: []int{16, 32, 45, 80, 96, 112},
			x:  16,

			out: []int{32, 32, 32, 32, 32, 80, 80},
		},
		{
			m:  7,
			in: []int{16, 32, 45, 80, 96, 112},
			x:  32,

			out: []int{45, 45, 45, 45, 80, 80, 96},
		},
	}

	for _, ts := range testcases {
		got := successors(ts.m, ts.in, ts.x)
		require.Equal(t, ts.out, got)
	}
}

func TestSuccessors_panicNotSorted(t *testing.T) {
	m := 8
	in := []int{45, 32, 99, 132, 199, 234}
	x := 45

	panicStr := "input array must be sorted in increasing order"

	defer func() {
		r := recover()
		require.NotNil(t, r)
		require.Equal(t, panicStr, fmt.Sprintf("%s", r))
	}()
	_ = successors(m, in, x)
}

func TestSuccessors_panicElementNotFound(t *testing.T) {
	m := 8
	in := []int{32, 45, 99, 132, 199, 234}
	x := 999

	panicStr := "x is not in array in"

	defer func() {
		r := recover()
		require.NotNil(t, r)
		require.Equal(t, panicStr, fmt.Sprintf("%s", r))
	}()
	_ = successors(m, in, x)
}

func TestPredecessors(t *testing.T) {
	testcases := []struct {
		m  int
		in []int
		x  int

		out []int
	}{
		{
			m:  9,
			in: []int{1, 12, 123, 234, 345, 456, 501},
			x:  234,

			out: []int{1, 12, 123, 456},
		},
		{
			m:  8,
			in: []int{32, 45, 99, 132, 199, 234},
			x:  45,

			out: []int{32, 234},
		},
	}

	for _, ts := range testcases {
		got := predecessors(ts.m, ts.in, ts.x)
		require.Equal(t, ts.out, got)
	}
}

func TestPredecessors_panicNotSorted(t *testing.T) {
	m := 8
	in := []int{45, 32, 99, 132, 199, 234}
	x := 45

	panicStr := "input array must be sorted in increasing order"

	defer func() {
		r := recover()
		require.NotNil(t, r)
		require.Equal(t, panicStr, fmt.Sprintf("%s", r))
	}()
	_ = predecessors(m, in, x)
}

func TestPredecessors_panicElementNotFound(t *testing.T) {
	m := 8
	in := []int{32, 45, 99, 132, 199, 234}
	x := 999

	panicStr := "x is not in array in"

	defer func() {
		r := recover()
		require.NotNil(t, r)
		require.Equal(t, panicStr, fmt.Sprintf("%s", r))
	}()
	_ = predecessors(m, in, x)
}
