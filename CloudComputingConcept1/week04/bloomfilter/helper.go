package bloomfilter

func defaultHashfuncGenerator(i int64, m int) func(int64) int {
	return func(x int64) int {
		n := int64(m)
		x = x % n
		return int((((x*x)%n + (x*x*x)%n) * i) % n)
	}
}
