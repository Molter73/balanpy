#!/usr/bin/env python3

import argparse
from datetime import datetime, timedelta
from random import randrange
import json


def generate_data(n: int, lower: int, upper: int) -> list:
    now = datetime.now()
    fecha_fmt = "%Y-%m-%dT%H:%M:%S"

    data = [
        {
            'fecha': (now - timedelta(0, (i * 15))).strftime(fecha_fmt),
            'dato': randrange(lower, upper),
        } for i in range(n)
    ]

    data.reverse()
    return data


def dump(output: str, data: list):
    with open(output, 'w') as f:
        json.dump(data, f)


def main(events: int, lower: int, upper: int, output: str):
    data = generate_data(events, lower, upper)

    if output is None:
        print(json.dumps(data))
    else:
        dump(output, data)


if __name__ == '__main__':
    parser = argparse.ArgumentParser()

    count_help = 'Number of events to generate'
    parser.add_argument('-c', '--count', help=count_help, type=int, default=10)

    lower_help = 'Set the lower bound for generated date'
    parser.add_argument('-l', '--lower', help=lower_help,
                        type=int, default=350)

    upper_help = 'Set the lower bound for generated date'
    parser.add_argument('-u', '--upper', help=lower_help,
                        type=int, default=400)

    output_help = 'Specify file to write into'
    parser.add_argument('-o', '--output', help=output_help,
                        type=str, default=None)

    args = parser.parse_args()

    events = args.count
    lower_bound = args.lower
    upper_bound = args.upper
    output = args.output
    main(events, lower_bound, upper_bound, output)
