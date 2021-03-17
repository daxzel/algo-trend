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

func Vector2String(data []float64) string {
	s := ""
	for _, n := range data {
		s += fmt.Sprintf("%f | ", n)
	}
	return s
}

func DotProduct(x []float64, y []float64) float64 {
	result := 0.0
	for j := 0; j < len(x); j++ {
		result += x[j] * y[j]
	}
	return result
}

func Minus(x []float64, y []float64) []float64 {
	result := make([]float64, len(x))
	for j := 0; j < len(x); j++ {
		result[j] = x[j] - y[j]
	}
	return result
}

func Power(x []float64, power float64) []float64 {
	result := make([]float64, len(x))
	for j := 0; j < len(x); j++ {
		result[j] = math.Pow(x[j], power)
	}
	return result
}

func Sum(x []float64) float64 {
	result := 0.0
	for j := 0; j < len(x); j++ {
		result += x[j]
	}
	return result
}

func GetColumn(x [][]float64, column int) []float64 {
	result := make([]float64, len(x))
	for j := 0; j < len(x); j++ {
		result[j] = x[j][column]
	}
	return result
}

func DotProductMatrix(x [][]float64, y [][]float64) [][]float64 {
	result := make([][]float64, len(x))
	for i := 0; i < len(x); i++ {
		result[i] = make([]float64, len(y))
		for j := 0; j < len(y[0]); j++ {
			result[i][j] = DotProduct(x[i], GetColumn(y, j))
		}
	}
	return result
}

func NormalizeVector(x []float64) [] float64 {
	norm := math.Pow(DotProduct(x, x), 0.5)

	for j := 0; j < len(x); j++ {
		x[j] = x[j] / norm
	}
	return x
}

func Copy(data [][]float64) [][]float64 {
	duplicate := make([][]float64, len(data))
	for i := range data {
		duplicate[i] = make([]float64, len(data[i]))
		copy(duplicate[i], data[i])
	}
	return duplicate
}

/* Find an orthonormal basis for the set of vectors q
 * using the Gram-Schmidt Orthogonalization process */
func GramSchimdt(data [][]float64) [][]float64 {
	result := Copy(data)
	for i := 1; i < len(result); i++ {
		for j := 0; j < i; j++ {
			factor := DotProduct(result[j], result[i]) / DotProduct(result[j], result[j])
			/* Subtract each scaled component of q_j from q_i */
			for k := 0; k < len(result); k++ {
				result[i][k] -= factor * result[j][k]
			}
		}
	}

	for i := 0; i < len(result); i++ {
		result[i] = NormalizeVector(result[i])
	}
	return result
}

func Transpose(data [][]float64) [][]float64 {
	result := Copy(data)
	for i := 0; i < len(result); i++ {
		for j := i; j < len(result); j++ {
			temp := result[i][j]
			result[i][j] = result[j][i]
			result[j][i] = temp
		}
	}
	return result
}

func Diag(data [][]float64) []float64 {
	result := make([]float64, len(data))
	for i := 0; i < len(result); i++ {
		result[i] = data[i][i]
	}
	return result
}

type EigenSummary struct {
	EigenVectors [][]float64
	EigenValues []float64
}

func Eigen(data [][]float64) EigenSummary {
	Q := Transpose(GramSchimdt(data))
	E := DotProductMatrix(DotProductMatrix(Transpose(Q), data), Q)
	U := Q
	result := Diag(E)
	init := Diag(data)
	for Sum(Power(Minus(init, result), 2.0)) > 1e-20 {
		init = result
		Q = Transpose(GramSchimdt(E))
		E = DotProductMatrix(DotProductMatrix(Transpose(Q), E), Q)
		U = DotProductMatrix(U, Q)
		result = Diag(E)
	}
	return EigenSummary{U, result}
}

func main() {

	// https://en.wikipedia.org/wiki/Principal_component_analysis

	input2 := [][]float64{
		{1, 2, 4, 6},
		{1, 6, 7, 8},
		{1, 1, 1, 10},
		{4, 2, 1, 1},
	}

	E := Eigen(CovMatrix(Normalize(input2)))

	fmt.Print(Matrix2String(E.EigenVectors))
	fmt.Print("\n----------------------\n")
	fmt.Print(Vector2String(E.EigenValues))
	fmt.Print("\n----------------------\n")
}