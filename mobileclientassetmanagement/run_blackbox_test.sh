#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$SCRIPT_DIR"
TEST_DIR="$PROJECT_DIR/test"
JUNIT_JAR="$PROJECT_DIR/lib/randoop-all-4.3.2.jar"
REPORT_DIR="$PROJECT_DIR/reports"
COVERAGE_DIR="$REPORT_DIR/coverage"

echo $SCRIPT_DIR
test_classes_array=()

for test_class in "$PROJECT_DIR/out/test/mobileclientassetmanagement"/blackboxtest/*.class; do
    full_path=$(dirname "$test_class")
    class_name=$(basename "$test_class" | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | grep -o '^[^_]*_[^_]*')
    if [ -n "$class_name" ]; then
        modified_path="$full_path/$class_name"
        test_classes_array+=("$modified_path")
    fi
done


total_test_count_present=0
total_test_count_executed=0

for test_class in "${test_classes_array[@]}"; do
  class_name="handler.$(basename -s .class $test_class)"
  echo "Running tests for class: $class_name"
  java -cp "$JUNIT_JAR:$PROJECT_DIR/out/production/mobileclientassetmanagement:$PROJECT_DIR/out/test/mobileclientassetmanagement:$PROJECT_DIR/lib/opencsv-3.8.jar:$TEST_DIR" \
            org.junit.runner.JUnitCore "blackboxtest.$(basename -s .class $test_class)"
  test_output=$(java -cp "$JUNIT_JAR:$PROJECT_DIR/out/production/mobileclientassetmanagement:$PROJECT_DIR/out/test/mobileclientassetmanagement:$PROJECT_DIR/lib/opencsv-3.8.jar:$TEST_DIR" \
          org.junit.runner.JUnitCore "blackboxtest.$(basename -s .class $test_class)")
  method_count_excluding_setup_and_constructor=$(javap -p "$test_class" | grep -oE 'public void test[^;]+;' | wc -l | awk '{$1=$1};1')
  test_case_count=$(echo "$test_output" | grep -o 'OK ([0-9]\+' | tr -d 'OK ()')
  echo "No of Tests present in the class $(basename -s .class $test_class): $method_count_excluding_setup_and_constructor"
  echo "No of Test Cases Executed Successfully For the class $(basename -s .class $test_class): $test_case_count"
  total_test_count_present=$((total_test_count_present+method_count_excluding_setup_and_constructor))
  total_test_count_executed=$((total_test_count_executed+test_case_count))
  sleep 3
done

echo
echo
echo "Total Test Cases Present: " $total_test_count_present
echo "Total Test Cases Executed: " $total_test_count_present
echo