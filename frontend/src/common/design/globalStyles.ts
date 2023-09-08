import { StyleSheet } from 'react-native';
import colors from './colors';

export const globalStyles = StyleSheet.create({
  whiteBackground: { backgroundColor: colors.white, minHeight: '100%' },
  container: {
    padding: 10,
    justifyContent: 'center',
  },
  h1: {
    fontSize: 30,
    fontFamily: 'IBMPlexSansKR-Bold',
    includeFontPadding: false,
  },
  h2: {
    fontSize: 24,
    fontFamily: 'IBMPlexSansKR-Bold',
    includeFontPadding: false,
  },
  h3: {
    fontSize: 18,
    fontFamily: 'IBMPlexSansKR-Bold',
    includeFontPadding: false,
  },
  bold: {
    fontSize: 15,
    fontFamily: 'IBMPlexSansKR-SemiBold',
    includeFontPadding: false,
    letterSpacing: 1,
  },
  p: {
    fontSize: 15,
    fontFamily: 'IBMPlexSansKR-Regular',
    includeFontPadding: false,
    letterSpacing: 1,
  },
});
