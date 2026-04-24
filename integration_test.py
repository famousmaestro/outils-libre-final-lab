import subprocess
import sys

def run_test(prices, quantities, customer_type, discount_code, expected_final):
    args_str = f"{prices} {quantities} {customer_type} {discount_code}"
    cmd = f".\\gradlew.bat -q run --args=\"{args_str}\""
    
    print(f"Running test with args: {args_str}")
    
    try:
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, check=True)
        output = result.stdout.strip()
        
        print("Output was:")
        print(output)
        
        # Verify required strings are in output
        assert "Subtotal:" in output
        assert "Discount amount:" in output
        assert "Tax:" in output
        assert "Final price:" in output
        
        # Parse final price
        final_price_line = [line for line in output.split('\n') if line.startswith("Final price:")][0]
        final_price = float(final_price_line.split(":")[1].strip())
        
        if abs(final_price - expected_final) < 0.01:
            print("=> TEST PASSED\n")
            return True
        else:
            print(f"=> TEST FAILED: Expected {expected_final}, got {final_price}\n")
            return False
            
    except subprocess.CalledProcessError as e:
        print(f"Command failed with exit code {e.returncode}")
        print("Stdout:", e.stdout)
        print("Stderr:", e.stderr)
        return False

def main():
    success = True
    
    # Test 1: 100, 50 -> Subtotal 150.
    # Quantities: 1, 2 -> 100*1 + 50*2 = 200.
    # Customer: REGULAR, Discount: NONE
    # Expected final: 200 - 0 (discount) = 200 + 19% tax(38) = 238
    success &= run_test("100,50", "1,2", "REGULAR", "NONE", 238.0)
    
    # Test 2: Subtotal 200
    # Customer VIP (5%), Discount SAVE10 (10%) -> Total discount 15% of 200 = 30
    # After discount: 170. Tax: 170 * 0.19 = 32.3.
    # Final price: 170 + 32.3 = 202.3
    success &= run_test("100,50", "1,2", "VIP", "SAVE10", 202.3)
    
    if success:
        print("ALL TESTS PASSED!")
        sys.exit(0)
    else:
        print("SOME TESTS FAILED!")
        sys.exit(1)

if __name__ == "__main__":
    main()
