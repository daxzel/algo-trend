package main

import (
	"fmt"
	"math"
)

// https://en.wikipedia.org/wiki/Principal_component_analysis

func Square(x float64) float64 {
	return x * x
}

func Abs(x float64) float64 {
	if x < 0 {
		return -x
	}
	return x
}

func Mean(data []float64) float64 {
	sum := 0.0
	for i := 0; i < len(data); i++ {
		sum += data[i]
	}
	return sum / float64(len(data))
}

func SD(data []float64) float64 {
	m := Mean(data)

	sumSquares := 0.0
	for i := 0; i < len(data); i++ {
		sumSquares += Square(Abs(data[i] - m))
	}
	return math.Pow(sumSquares / float64(len(data)), 0.5)
}

func Cov(x []float64, y []float64) float64 {
	xMean := Mean(x)
	yMean := Mean(y)

	result := 0.0

	for i := 0; i < len(x); i++ {
		result += (x[i] - xMean) * (y[i] - yMean)
	}

	return result / float64(len(x))
}

func CovMatrix(data [][]float64) [][]float64 {
	result := make([][]float64, len(data))
	for i := 0; i < len(result); i++ {
		result[i] = make([]float64,  len(data))
		for j := 0; j < len(result); j++ {
			result[i][j] = Cov(data[i], data[j])
		}
	}
	return result
}

// rows are features, columns records
func PCA(data [][]float64) {
	// TODO
}

func Normalize(data [][]float64) [][]float64 {
	for i := 0; i < len(data); i++ {
		m := Mean(data[i])
		sd := SD(data[i])
		for j := 0; j < len(data[i]); j++ {
			data[i][j] = ( data[i][j] - m ) / sd
		}
	}
	return data
}

func Matrix2String(data [][]float64) string {
	s := ""
	for i := range data {
		s += "| "
		for _, n := range data[i] {
			s += fmt.Sprintf("%f | ", n)
		}
		s += "\n"
	}
	return s
}

func main() {
	input := [][]float64{
		{0, 1, 2, 3},
		{0, 1, 4, 9},
		{3, 2, 1, 1},
	}

	fmt.Print(Matrix2String(CovMatrix(Normalize(input))))
}